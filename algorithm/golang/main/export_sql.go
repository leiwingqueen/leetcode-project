package main

import (
	"encoding/csv"
	"fmt"
	"os"
	"strings"
)

// 推球机数据导出

func main() {
	file, err := os.Open("/Users/liyongquan/Desktop/龙珠3.16-21.csv") // 请将 "your_csv_file.csv" 替换为您的 CSV 文件路径
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

	var sqlQueries []string

	for i, record := range records {
		if i == 0 {
			continue // 跳过第一行，因为它是表头
		}

		kugouID := record[0]
		startTime := record[1]
		endTime := record[2]

		sqlQuery := fmt.Sprintf(`SELECT deviceid,orderId, num, gameid, FROM_UNIXTIME(createTime/1000), stat
FROM t_freeze_order_202303
WHERE createTime >= UNIX_TIMESTAMP('%s') * 1000
AND createTime < UNIX_TIMESTAMP('%s') * 1000
AND kugouid = %s`, startTime, endTime, kugouID)

		sqlQueries = append(sqlQueries, sqlQuery)
	}

	finalSQL := strings.Join(sqlQueries, "\nUNION ALL\n")
	fmt.Println(finalSQL)
}
