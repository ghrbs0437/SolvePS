def solution(signals):
    answer = 0
    divider = []
    
    for signal in signals:
        divider.append(sum(signal))

    roof_check = 1
    for div in divider:
        roof_check *= div
    
    states = [[]*len(signals)]*(roof_check)
    

    
    time = 0
    while True :
        state = []
        for div in divider:
            state.append(time % div)
            
        flag = True
        for i, signal in enumerate(signals):
            if not (state[i] > signals[i][0]) or not (state[i] <= signals[i][0] + signals[i][1]):
                flag = False
                break
                
        if flag:
            return time
        
        if state == states[time%roof_check]:
            return -1
        states[time%roof_check] = state
        time += 1
    
    return -1