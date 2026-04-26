def solution(n, w, num):
    ySize = n // w
    if n % w != 0 :
        ySize +=1
    xSize = w
    arr = [[0] * xSize for _ in range(ySize)]
    
    dy = 1
    dx = 1
    x = 0
    y = 0
    i = 0
    while i < n:
        i += 1
        arr[y][x] = i
        if i % w == 0:
            dx *= -1
            y += dy
        else:
            x += dx 
            
    # print(arr)
    
    depth = 0 
    xpos = 0
    for xrow in arr:
        if num in xrow:
            xpos = xrow.index(num)
            break
        depth += 1
    
    answer = 0
    for i in range(depth,ySize):
        if arr[i][xpos] == 0:
            break
        answer+=1
    
    return answer