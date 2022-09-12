![image](https://user-images.githubusercontent.com/33195517/189590381-d21f3db9-e4eb-40b5-adc9-1d184df8112b.png)
![image](https://user-images.githubusercontent.com/33195517/189590440-937a9c6b-c9a2-4fa0-aed1-d5d53e1b9931.png)

투포인터</br>
1. 두 큐를 하나의 큐로 합친다.
2. low를 첫번째 큐의 시작, high를 첫번째 큐의 마지막으로 초기화 한다.
3. sum을 low부터 high까지의 합으로 초기화한다.
4. l은 합친 큐의 마지막원소 까지 가능하므로 while문의 실행조건을 l<n, sum == S/2와 같으면 종료한다.
5. 만약 sum이 S/2보다 크다면 숫자를 빼줘야 하므로 S-=queue[l], l++
6. 만약 sum이 S/2보다 작다면 숫자를 더해줘야 하므로 h++, S+=queue[h]. 이때 h는 배열의 첫번째로 돌아갈 수 있으므로 h%n을 해준다.
7. 반목문을 실행할 때마다 수행횟수를 더해준다. ans++
8. 출력: S가 sum/2이 아니면 -1을 return, 아니면 ans를 return.
