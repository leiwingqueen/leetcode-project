package wc457

import "sort"

// 给你三个长度为 n 的数组，分别描述 n 个优惠券的属性：code、businessLine 和 isActive。其中，第 i 个优惠券具有以下属性：
//
//code[i]：一个 字符串，表示优惠券的标识符。
//businessLine[i]：一个 字符串，表示优惠券所属的业务类别。
//isActive[i]：一个 布尔值，表示优惠券是否当前有效。
//当以下所有条件都满足时，优惠券被认为是 有效的 ：
//
//code[i] 不能为空，并且仅由字母数字字符（a-z、A-Z、0-9）和下划线（_）组成。
//businessLine[i] 必须是以下四个类别之一："electronics"、"grocery"、"pharmacy"、"restaurant"。
//isActive[i] 为 true 。
//返回所有 有效优惠券的标识符 组成的数组，按照以下规则排序：
//
//先按照其 businessLine 的顺序排序："electronics"、"grocery"、"pharmacy"、"restaurant"。
//在每个类别内，再按照 标识符的字典序（升序）排序。
//
//
//示例 1：
//
//输入： code = ["SAVE20","","PHARMA5","SAVE@20"], businessLine = ["restaurant","grocery","pharmacy","restaurant"], isActive = [true,true,true,true]
//
//输出： ["PHARMA5","SAVE20"]
//
//解释：
//
//第一个优惠券有效。
//第二个优惠券的标识符为空（无效）。
//第三个优惠券有效。
//第四个优惠券的标识符包含特殊字符 @（无效）。
//示例 2：
//
//输入： code = ["GROCERY15","ELECTRONICS_50","DISCOUNT10"], businessLine = ["grocery","electronics","invalid"], isActive = [false,true,true]
//
//输出： ["ELECTRONICS_50"]
//
//解释：
//
//第一个优惠券无效，因为它未激活。
//第二个优惠券有效。
//第三个优惠券无效，因为其业务类别无效。
//
//
//提示：
//
//n == code.length == businessLine.length == isActive.length
//1 <= n <= 100
//0 <= code[i].length, businessLine[i].length <= 100
//code[i] 和 businessLine[i] 由可打印的 ASCII 字符组成。
//isActive[i] 的值为 true 或 false。

// 这道题真的恶心，纯纯业务逻辑
func validateCoupons(code []string, businessLine []string, isActive []bool) []string {
	n := len(code)
	var available []int
	checkCode := func(code string) bool {
		if code == "" {
			return false
		}
		for i := range code {
			ch := code[i]
			if !(ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z' || ch >= '0' && ch <= '9' || ch == '_') {
				return false
			}
		}
		return true
	}
	busiArr := []string{
		"electronics", "grocery", "pharmacy", "restaurant",
	}
	checkBusiness := func(line string) bool {
		for _, s := range busiArr {
			if s == line {
				return true
			}
		}
		return false
	}
	for i := 0; i < n; i++ {
		if isActive[i] && checkCode(code[i]) && checkBusiness(businessLine[i]) {
			available = append(available, i)
		}
	}
	if len(available) == 0 {
		return []string{}
	}
	sort.Slice(available, func(i, j int) bool {
		id1, id2 := available[i], available[j]
		if businessLine[id1] != businessLine[id2] {
			return businessLine[id1] < businessLine[id2]
		} else {
			return code[id1] < code[id2]
		}
	})
	res := make([]string, len(available))
	for i := range available {
		res[i] = code[available[i]]
	}
	return res
}
