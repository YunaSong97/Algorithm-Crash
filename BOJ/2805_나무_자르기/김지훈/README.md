(1 ≤ N ≤ 1,000,000, 1 ≤ M ≤ 2,000,000,000 이고 제한시간이 1초이기 때문에 시간초과를 피하고자 파라메트릭 서치 진행</br> 
1. 높이는 1,000,000,000 이하 임으로 int형의 크기를 넘어갈 수 있어 long타입으로 받아야함</br>
2. sum 값이 M값과 같아도 가장 큰 값을 찾는것이기 때문에  while문을 종료하지 않음</br>

start=1, mid(start+end의 중간값), end(나무 배열의 최대크기) </br>
mid값을 기준으로 자른 나무의 총합이 M보다 크다면 너무 작게 자른것 임으로 start를 mid+1로 높이를 올림</br>
mid값을 기준으로 자른 나무의 총합이 M보다 작다면 너무 크게 자른것 임으로 end를 mid-1로 높이를 내림</br>
start값이 end보다 크거나 같아진다면 모두 탐색한 것임으로 반복문 종료후 end값 출력</br>
<img width="195" alt="나무 자르기" src="https://user-images.githubusercontent.com/68943993/188421268-0cc22581-9a34-4933-b668-384379a5c6ef.PNG"> 정렬사용</br>

<img width="197" alt="나무 자르" src="https://user-images.githubusercontent.com/68943993/188422171-6c7699a3-6463-4fa4-bbdc-a1e960b1283d.PNG">정렬 사용 X
