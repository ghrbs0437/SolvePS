# return = 상품을 받을 직원의 수
def solution(schedules, timelogs, startday):
    answer = 0
    # schedules = 직원 각각의 출근 희망시간
    # timelogs = 개별 직원의 일주일 출근 성적
    # startday = 1 월 7 일요일, 시작날자 offset
    # print(schedules)
    # print(timelogs)
    # print(startday)
    size = len(schedules)
    
    for i in range(size):
        flag = True
        for j in range(7):
            if (j+startday) % 7 == 6 or (j+startday) % 7 == 0:
                continue
            
            timelimit = schedules[i] +10
            if (timelimit%100) >= 60:
                schedules[i]+=40
                schedules[i]%=2400
            if timelogs[i][j] > (schedules[i] +10):
                flag = False;
        if flag:
            answer+=1
    return answer