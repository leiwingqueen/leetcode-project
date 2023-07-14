package main

import (
	"encoding/csv"
	"fmt"
	"os"
	"strings"
)

// 推球机数据导出

func main() {
	file, err := os.Open("/Users/liyongquan/Desktop/7.1-7.12.csv") // 请将 "your_csv_file.csv" 替换为您的 CSV 文件路径
	if err != nil {
		fmt.Println("Error opening file:", err)
		return
	}
	defer file.Close()

	reader := csv.NewReader(file)
	records, err := reader.ReadAll()
	if err != nil {
		fmt.Println("Error reading file:", err)
		return
	}
	month := "202307"
	// sql := genSql1(records, month)
	sql := genSql2(records, month)
	fmt.Println(sql)

	// "SELECT gameid,deviceid,kugouId,score,costNum,FROM_UNIXTIME(createTime/1000) FROM `t_game_round_info_202303` limit 10"
}

// 查询扣费订单
func genSql1(records [][]string, month string) string {
	var sqlQueries []string

	for i, record := range records {
		if i == 0 {
			continue // 跳过第一行，因为它是表头
		}
		kugouID := record[0]
		startTime := record[1]
		endTime := record[2]
		if kugouID == "" {
			continue
		}

		sqlQuery := fmt.Sprintf(`SELECT deviceid,orderId,kugouId, num, gameid, FROM_UNIXTIME(createTime/1000), stat
FROM t_freeze_order_%s
WHERE createTime >= UNIX_TIMESTAMP('%s') * 1000
AND createTime < UNIX_TIMESTAMP('%s') * 1000
AND kugouid = %s`, month, startTime, endTime, kugouID)

		sqlQueries = append(sqlQueries, sqlQuery)
	}
	return strings.Join(sqlQueries, "\nUNION ALL\n")
}

// 查询该场次对应的发奖
func genSql2(records [][]string, month string) string {
	var sqlQueries []string
	for i, record := range records {
		if i == 0 {
			continue // 跳过第一行，因为它是表头
		}
		kugouID := record[0]
		startTime := record[1]
		endTime := record[2]
		if kugouID == "" {
			continue
		}

		sqlQuery := fmt.Sprintf(`SELECT gameid
FROM t_freeze_order_%s
WHERE createTime >= UNIX_TIMESTAMP('%s') * 1000
AND createTime < UNIX_TIMESTAMP('%s') * 1000
AND kugouid = %s`, month, startTime, endTime, kugouID)
		sqlQueries = append(sqlQueries, sqlQuery)
	}
	finalSQL := strings.Join(sqlQueries, "\nUNION ALL\n")
	return fmt.Sprintf("SELECT gameid,deviceid,kugouId,score,costNum,FROM_UNIXTIME(createTime/1000) "+
		"FROM `t_game_round_info_%s` "+
		"where gameid in (select distinct(t.gameid) from (%s) as t)", month, finalSQL)
}
