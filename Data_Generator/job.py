from skill import skill
class job:

	def __init__(self, job_id, co_id, job_title, job_desc):

		self.j_id = job_id
		self.c_id = co_id
		self.title = job_title.rstrip()
		self.desc = job_desc.rstrip()
		self.skills = []

	def ret_SQL(self):
		return ('(%d,%d,%s,%s)') % (int(self.j_id), int(self.c_id), self.title, self.desc)

	def set_des_skills(self, skillz):
		self.skills = skillz 

