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

# nginx 설치

