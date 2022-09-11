# 2805 나무 자르기

![캡처](https://user-images.githubusercontent.com/72604908/189536094-fe3285b2-9e88-4be3-b53c-da41db6ac1a4.PNG)

### # main()
1. 입력받아서 정렬한다.

### # bs()
1. left=가장 작은 값, right=가장 큰값+1, mid=(left+right)/2
2. mid보다 긴 나무가 있다면 (나무길이-mid) 값을 cnt에 누적하여 더함
3. 합이 m보다 작으면 mid의 값을 낮춰주기 위해 right=mid, 그 반대라면 left=mid+1
4. 높이 최댓값을 지정하기 위해 left 반환
