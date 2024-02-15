# EC2 서버 생성
프리티어로 사용

Ubuntu 사용

보안 규칙 설정

# docker 설치
```bash
# 시스템 업데이트
sudo apt update 

# 의존성 패키지 설치
sudo apt install -y apt-transport-https ca-certificates curl software-properties-common


# gpg 키 설정
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

# docker repo 등록
sudo add-apt-repository --yes \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"

# 도커 엔진 설치
sudo apt-get install docker-ce docker-ce-cli containerd.io -y

# 도커 버전 확인
docker -v

# 실행 상태 확인
sudo systemctl status docker

# 도커 실행
sudo docker run hello-world

```

`$(lsb_release -cs)` 는 무엇인가? = 우분투 버전을 확인하는 명령어라 한다

# 젠킨스 설치

```bash
# 젠킨스 컨테이너 생성
sudo docker run -d --name jenkins -p 8080:8080 jenkins/jenkins
# -d: 컨테이너를 데몬으로 실행
# --name: 컨테이너 이름
# -p 8080:8080 외부 통신 포트와 내부 포트 포워딩
```

\${ip}:8080 으로 젠킨스 접속

```bash
# 우분투 - 도커 젠킨스 접속
sudo docker exec -it jenkins bash

# 비밀번호 확인
cat /var/jenkins_home/secrets/initialAdminPassword

```

왼쪽 추천 플러그인 설치로 진행



# nginx 설치

