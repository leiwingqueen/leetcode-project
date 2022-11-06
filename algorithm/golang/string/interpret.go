package string

func interpret(command string) string {
	n := len(command)
	i := 0
	res := make([]byte, 0)
	for i < n {
		if command[i] == 'G' {
			res = append(res, 'G')
			i++
		} else {
			if command[i+1] == ')' {
				res = append(res, 'o')
				i += 2
			} else {
				res = append(res, 'a', 'l')
				i += 4
			}
		}
	}
	return string(res)
}
