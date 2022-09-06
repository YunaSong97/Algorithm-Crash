![image](https://user-images.githubusercontent.com/33195517/188528988-7b433e35-f62f-4ee6-9a34-97421c7fc651.png)
1. 나무를 자르는 높이를 mid = (low + high)/2 로 설정한다.
2. 잘린 나무 합이 M보다 크면 높이를 높여야하니까 low = mid + 1, ans 갱신
3. 잘린 나무 합이 M보다 작으면 높이를 낮춰야하니까 high = mid + 1
4. 합이 M이면 최대 높이이므로 ans = mid하고 반복문 종료
