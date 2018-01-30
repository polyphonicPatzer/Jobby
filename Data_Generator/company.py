from job import job
class company:
	
	def __init__(self, co_id, co_name):

		self.id = co_id
		self.name = co_name.rstrip()
		self.act_stat = 1
		self.password = '123'
		self.jobs = []

	def ret_SQL(self):
		return ('(%d,%s,%d,%s)') % (self.id, self.name, self.act_stat, self.password)

	def set_jobs(self, jobs):
		self.jobs = jobs



