from collections import Counter

def solution(message, spoiler_ranges):
    answer = 0
    normal_string = set([])
    spo_string = []
    
    mask = [False]* len(message)  
    
    for spoiler_range in spoiler_ranges:
        for i in range(spoiler_range[0], spoiler_range[1]+1):
            mask[i] = True
    
    build_str = ""
    flag = False
    
    for i in range(len(message)):
        if message[i] == ' ':
            if flag :
                spo_string.append(build_str)
            else :
                normal_string.add(build_str)
            build_str = ""
            flag = False
        else:
            build_str += message[i]
            if mask[i]:
                flag = True
    

    if flag :
        spo_string.append(build_str)
    else :
        normal_string.add(build_str)
            
            
    count = Counter(spo_string)
    
    for key in count:
        if count[key] >= 1 and key not in normal_string:
            answer+=1
    
    return answer