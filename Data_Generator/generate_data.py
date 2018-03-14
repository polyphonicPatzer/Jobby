from company import company
from job import job
from desired_skill import desired_skill
from candidate_skill import candidate_skill
from skill import skill
from candidate import candidate
from reference import reference
import random

def pick_jobs(amount):
	o = open('jobs.txt', 'r')
	data = o.readlines()
	jobs = random.choices(data, k = int(amount))
	return (jobs)


def pick_companies(amount):
	o = open('companies.txt', 'r')
	data = o.readlines()
	companies = random.choices(data, k = int(amount))
	return(companies)

def pick_skills(amount):
	o = open('skills.txt', 'r')
	data = o.readlines()
	skills = random.choices(data, k = int(amount))
	return(skills)

def pick_candidates(amount):
	o = open('candidates.txt', 'r')
	data = o.readlines()
	candidates = random.choices(data, k = int(amount))
	return (candidates)

def gen_companies(companies):
	comps = []
	for x in range (0,len(companies)): 
		new_comp = company(x,companies[x])
		comps.append(new_comp)
	return comps

def gen_skills(skills):
	skillz = []
	for x in range (0,len(skills)):
		splt = skills[x].split(',')
		new_skill = skill(splt[0],splt[1], splt[2], splt[3])
		skillz.append(new_skill)
	return skillz

def gen_jobs(jobs, co_id):
	jb = []
	for x in range (0,len(jobs)):
		splt = jobs[x].split(',')
		new_job = job(splt[2],co_id,splt[0], splt[1])
		jb.append(new_job)
	return jb

def gen_candidates(candidates):
	cn = []
	for x in range (0,len(candidates)):
		splt = candidates[x].split(',')
		new_cand = candidate(x, splt[0], splt[1])
		cn.append(new_cand)
	return cn

# def gen_desired_skills():

# def gen_candidate_skill():


# def gen_references():

def Main():
	num_companies = input("Enter number of companies you want: ")
	companies = gen_companies(pick_companies(num_companies))
	for y in range(0, len(companies)):
		co_jobs = gen_jobs(pick_jobs(3), companies[y].id) #this number will change the amount of jobs per company
		for x in range (0,len(co_jobs)):																			# > Either of these could be random 
			jb_skills = gen_skills(pick_skills(3)) #This number will change the amount of skills per job
			make_des = []
			for des in jb_skills:
				translated = desired_skill(co_jobs[x].c_id, des.id, 33)
				make_des.append(translated)

			co_jobs[x].set_des_skills(make_des) 
		companies[y].set_jobs(co_jobs)

	candidates = gen_candidates(pick_candidates(num_companies*5))

	b = open('output3.txt', 'w')
	for candidate in candidates:
		b.write(candidate.ret_SQL() + ",\n")


	o = open('output.txt', 'w')

	jobs = []
	skills = []

	for company in companies:
		#print(company.ret_SQL() + "\n")
		o.write(company.ret_SQL() + "\n")
		for job in company.jobs:
			#print(job.ret_SQL() + "\n     ")
			o.write("     ")
			o.write(job.ret_SQL() + "\n")
			jobs.append(job)
			for skill in job.skills:
				#print(skill.ret_SQL() + "\n          ")
				o.write("          ")
				o.write(skill.ret_SQL() + "\n")
				skills.append(skill)
	o.close()

	a = open('output2.txt', 'w')

	a.write("Companies \n")
	a.write("------------------------------------ \n")
	for company in companies:
		a.write(company.ret_SQL() + ",\n")
	a.write('\n')
	a.write("Jobs \n")
	a.write("------------------------------------ \n")
	for job in jobs:
		a.write(job.ret_SQL() + ",\n")
	a.write('\n')
	a.write("Skills \n")
	a.write("------------------------------------ \n")
	for skill in skills:
		a.write(skill.ret_SQL() + ",\n")


Main()