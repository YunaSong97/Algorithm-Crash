# 64062 징검다리 건너기

![캡처](https://user-images.githubusercontent.com/72604908/190892496-19aef6bc-a216-4c4b-a4f7-4ecf7c4fc745.PNG)
![2](https://user-images.githubusercontent.com/72604908/190892498-33c1ac88-1d2e-4e3b-89b9-aaaf4af3da24.PNG)
<br/>
![3](https://user-images.githubusercontent.com/72604908/190892500-83da526e-1233-4fa9-a3f3-577e2496e953.PNG)

### # solution()
1. mid = (배열값중 가장 큰수+가장작은수)/2  --> 건널 수 있는 친구들의 수
2. 디딤돌의 수 만큼 반복문 돌리기
3. 만약 디딤돌에 적힌 수 - mid가 음수라면 cnt++;
4. cnt == k라면 징검다리 못건넘
5. 징검다리를 못건넌다면 친구 수가 많은거니까 max = mid-1 <br/>
   징검다리를 건널 수 있다면 친구수가 적다는 거니까 min = mid+1, answer값 갱신
