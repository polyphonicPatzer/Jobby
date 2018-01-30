class desired_skill:

	def __init__(self, job_id, skill_id, weight):
		self.id = job_id
		self.skill_id = skill_id
		self.weight = weight

	def ret_SQL(self):
		return ('(%d,%d,%d)') % (self.id, int(self.skill_id), self.weight)

