class skill:

	def __init__(self, skl_name, skl_type, skl_desc, skl_id):

		self.name = skl_name.rstrip()
		self.type = skl_type.rstrip()
		self.desc = skl_desc.rstrip()
		self.id = skl_id

	def ret_SQL(self):
		return ('(%s,%d,%d,%s)') % (self.name, self.id, self.type, self.desc)

