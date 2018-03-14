def quickfix():
	end = []
	o = open('candidates.txt', 'r')
	a = open('candidates2.txt', 'w')
	data = o.readlines()
	for x in data:
		x = x.rstrip() + ', ' + x.rstrip() + '@gmail.com, resume, 123 \n'
		end.append(x)

	for y in end:
		a.write(y)

quickfix()
