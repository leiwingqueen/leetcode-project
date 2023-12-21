package webchat

func convert(src string) string {
	n := len(src)
	var res []byte
	con := func(ch byte) int {
		num := 0
		if ch >= 'a' && ch <= 'f' {
			num = int(ch - 'a' + 10)
		} else if ch >= 'A' && ch <= 'F' {
			num = int(ch - 'A' + 10)
		} else {
			num = int(ch - '0')
		}
		return num
	}
	i := 0
	for i < n {
		num1 := con(src[i])
		num2 := 0
		if i+1 < n {
			num2 = con(src[i+1])
		}
		res = append(res, (byte)((num1<<4)+num2))
		i += 2
	}
	return string(res)

}
