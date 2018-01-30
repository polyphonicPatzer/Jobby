class candidate_skill:

	def __init__ (self, cand_id, skl_name, cand_skl_rating):
		self.id = cand_id
		self.name = skl_name
		self.skl_rating = cand_skl_rating

	def ret_SQL(self):
		return ('(%d,%s,%d)') % (self.id, self.name, self.skl_rating)

		