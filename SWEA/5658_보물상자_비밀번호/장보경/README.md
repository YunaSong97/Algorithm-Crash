# 5658 보물상자 비밀번호

![캡처](https://user-images.githubusercontent.com/72604908/195126190-3de66550-61f8-4ebf-9a51-61065385c00b.PNG)

### # main()
1. 입력받은 문자열을 2차원 배열로 만든다.
2. rotate()한 후, 그 결과를 ArrayList에 담아 K번째 위치를 get한다.
</br></br>

### # rotate()
1. 뚜껑을 한 번 돌릴때마다 그에 적합한 배열의 위치를 찾아내어 문자를 StringBuilder에 append한다.
2. StringBuilder에 저장한 문자열의 길이가 N/4만큼 되었다면 그 결과를 TreeSet에 넣어준다.
