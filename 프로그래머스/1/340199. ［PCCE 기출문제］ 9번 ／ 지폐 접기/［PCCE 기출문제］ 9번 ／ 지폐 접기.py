def solution(wallet : list, bill: list):
    answer = 0
    max_row = wallet[0]
    max_column = wallet[1]

    small = min(bill[0], bill[1])
    large = max(bill[0], bill[1])    
    while True:
        if max_row >= small and max_column >= large:
            break
        elif max_row >=large and max_column >= small:
            break
        else:
            large //=2
        if large < small:
            temp = small
            small = large
            large = temp
        # print(f"{small},{large}")
        answer+=1
    return answer