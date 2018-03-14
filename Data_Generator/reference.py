class reference:

	def __init__ (self, cand_id, ref_name, ref_phone, ref_email):

		self.id = cand_id
		self.name = ref_name
		self.phone = ref_phone
		self.email = ref_email

	def ret_SQL(self):
		return ('(%d,%s,%s,%s)') % (self.id, self.name, self.phone, self.email)

