class candidate:

	def __init__(self, cand_id, cand_name, cand_email):
		self.id = cand_id
		self.name = cand_name
		self.email = cand_email
		self.resume = '10/10 Trust Me' 
		self.password = '123'

	def ret_SQL(self):
		return ('(%d,%s,%s,%s,%s)') % (self.id, self.name, self.email, self.resume, self.password)