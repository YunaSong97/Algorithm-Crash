# 2470 두 용액

![캡처](https://user-images.githubusercontent.com/72604908/187842550-30f24d61-fb00-4927-8257-32af244acb77.PNG)

1. 용액 값을 ArrayList에 저장하여 정렬한다.
2. start가 end보다 작다면 반복문을 실행한다.
3. 이전값이 ArrayList의 start + end의 절대값보다 작다면 0에 근접하다는 이야기이므로 ans값을 바꾸고 이전값을 갱신한다.
4. 만약 ArrayList의 start + end 값이 음수라면 0에 근접할 수 있도록 더 큰 음수를 선택한다.
                                     양수라면 0에 근접할 수 있도록 더 작은 양수를 선택한다.
