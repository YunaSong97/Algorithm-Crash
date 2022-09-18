# 92341 주차 요금 계산

![캡처](https://user-images.githubusercontent.com/72604908/190890906-e75a056d-7f02-4b4e-a8d6-64bd353c81db.PNG)

### # solution()
1. 입차, 출차를 나누어 HashMap에 Key=차량번호, Value=Record(시간, 차량번호, 내역)을 저장한다.
2. 차량 번호 순으로 오름차순 정렬한 배열을 만든다.
3. 그 순서대로 요금을 계산한 후 ArrayList에 넣는다.
4. ArrayList에 넣은 값을 answer배열에 넣는다.
</br></br>

### # Record
1. 시간이 빠른 순으로 정렬하기 위한 클래스
