import csv

i = 0
users = {}
for line in open('data', 'r'):
    user = line[:-1].split(":")
    users[user[0]] = (user[2], user[1])
for line in open('gamelog', 'r'):
    log = line[:-1].split(":")
    num = len(log) - 5
    team1 = log[:int(num/2)]
    team2 = log[int(num/2):num]
    host = log[-1]
    ip = log[-2]
    hostname = log[-3]
    team2rate = -int(log[-4])
    team1rate = int(log[-5])
    win = 1
    if team1rate < -team2rate:
        team1rate = - team1rate
        team2rate = - team2rate
        win = 2

