# 1062 가르침

![캡처](https://user-images.githubusercontent.com/72604908/201896055-89148514-00cc-4bff-abc7-64eb6d335548.PNG)

### # main()
1. 무조건 a. n. t, i, c가 포함되어야 하기 때문에 K가 5 미만이면 0을 출력한다.
2. K==26이면 모든 알페벳을 배운 것이므로 dfs를 하지 않고 N을 출력한다.
3. a. n. t, i, c를 배운 알파벳으로 고정시킨다.
</br></br>

### # dfs()
1. 가르쳐야할 글자수를 채우면, teachCnt()를 실행하여 학생들이 몇 개의 단어를 읽을 수 있는지 확인 후 리턴한다.
2. dfs로 배울 알파벳을 선택한다.
</br></br>

### # teachCnt()
1. 단어에 포함된 문자를 배웠는지 확인하여 cnt를 증가시킨다.
