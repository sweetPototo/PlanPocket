$(document).ready(function() {
    // 처음에 제출 버튼 비활성화
    $('input[type="submit"]').prop('disabled', true);

    // 유효성 상태 추적
    var validationState = {
        username: false,
        name: false,
        password: false,
        confirmPassword: false,
        phone: false,
        email: false,
        verificationCode: false
    };

    // 유효성 상태에 따라 제출 버튼 활성화/비활성화 함수
    function toggleSubmitButton() {
        var allValid = Object.values(validationState).every(value => value === true);
        console.log('Validation State:', validationState); // 상태 출력
        console.log('Submit Button Enabled:', allValid); // 버튼 활성화 상태 출력
        $('input[type="submit"]').prop('disabled', !allValid);
    }

    // 아이디 유효성 검사 및 AJAX 체크
    $('input[name="username"]').on('input', function() {
        var username = $(this).val();
        var regex = /^[a-zA-Z0-9]*$/;

        if (username.length > 12) {
            username = username.substring(0, 12); // 최대 12글자로 자르기
            $(this).val(username); // 입력 필드에 반영
        }

        if (!regex.test(username)) {
            $('.message').text('아이디는 영어와 숫자만 입력할 수 있습니다.').removeClass('success').addClass('error');
            validationState.username = false;
        } else if (username === "") {
            $('.message').text('').removeClass('success error');
            validationState.username = false;
        } else {
            $.ajax({
                url: '/api/checkUsername',
                type: 'GET',
                data: { username: username },
                success: function(response) {
                    if (response === 'EXIST') {
                        $('.message').text('이미 존재하는 아이디입니다.').removeClass('success').addClass('error');
                        validationState.username = false;
                    } else if (response === 'AVAILABLE') {
                        $('.message').text('사용 가능한 아이디입니다.').removeClass('error').addClass('success');
                        validationState.username = true;
                    }
                    toggleSubmitButton();
                },
                error: function() {
                    console.error("AJAX 요청이 실패했습니다.");
                    validationState.username = false;
                    toggleSubmitButton();
                }
            });
        }
    });

    // 이름 유효성 검사 (한글만 허용)
    $('input[name="name"]').on('input', function() {
        var name = $(this).val().trim();
        var regex = /^[가-힣]*$/;

        if (name === "") {
            $('.name-message').text('').removeClass('success error');
            validationState.name = false;
        } else if (!regex.test(name)) {
            $('.name-message').text('이름은 한글만 입력할 수 있습니다.').removeClass('success').addClass('error');
            validationState.name = false;
        } else {
            $('.name-message').text('');
            validationState.name = true;
        }
        toggleSubmitButton();
    });

    // 비밀번호 유효성 검사
    $('input[name="password"]').on('input', function() {
        var password = $(this).val();
        var regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

        if (password === "") {
            $('.password-message').text('').removeClass('success error');
            validationState.password = false;
        } else if (!regex.test(password)) {
            $('.password-message').text('비밀번호는 영문자, 숫자, 특수문자를 포함하여 8자리 이상이어야 합니다.').removeClass('success').addClass('error');
            validationState.password = false;
        } else {
            $('.password-message').text('');
            validationState.password = true;
        }
        checkPasswordsMatch();
        toggleSubmitButton();
    });

    // 비밀번호 확인 유효성 검사
    $('input[name="confirm_password"]').on('input', function() {
        checkPasswordsMatch();
        toggleSubmitButton();
    });

    function checkPasswordsMatch() {
        var password = $('input[name="password"]').val();
        var confirmPassword = $('input[name="confirm_password"]').val();

        if (confirmPassword === "" || password === "") {
            $('.confirm-password-message').text('');
            validationState.confirmPassword = false;
        } else if (password !== confirmPassword) {
            $('.confirm-password-message').text('비밀번호가 일치하지 않습니다.').removeClass('success').addClass('error');
            validationState.confirmPassword = false;
        } else {
            $('.confirm-password-message').text('');
            validationState.confirmPassword = true;
        }
    }

    // 전화번호 유효성 검사 (숫자만 허용, 11자리 이하)
    $('input[name="phone"]').on('input', function() {
        var phone = $(this).val().replace(/\D/g, ''); // 숫자가 아닌 문자는 제거

        if (phone.length > 11) {
            phone = phone.substring(0, 11); // 11자리로 제한
        }

        $(this).val(phone);

        if (phone === "") {
            $('.phone-message').text('');
            validationState.phone = false;
        } else {
            $('.phone-message').text(''); // 오류 메시지를 비웁니다
            validationState.phone = true;
        }

        toggleSubmitButton();
    });

    // 이메일 유효성 검사 및 AJAX 체크
    $('input[name="email"]').on('input', function() {
        var email = $(this).val().trim();
        var regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (email === "") {
            $('.email-message').text('').removeClass('success error');
            validationState.email = false;
        } else if (!regex.test(email)) {
            $('.email-message').text('유효한 이메일 주소를 입력해 주세요.').removeClass('success').addClass('error');
            validationState.email = false;
        } else {
            $.ajax({
                url: '/api/validateEmail',
                type: 'GET',
                data: { email: email },
                success: function(response) {
                    if (response === 'VALID') {
                        $('.email-message').text('').removeClass('error').addClass('success');
                        validationState.email = true;
                    } else {
                        $('.email-message').text('유효하지 않은 이메일 주소입니다.').removeClass('success').addClass('error');
                        validationState.email = false;
                    }
                    toggleSubmitButton();
                },
                error: function() {
                    console.error("AJAX 요청이 실패했습니다.");
                    validationState.email = false;
                    toggleSubmitButton();
                }
            });
        }
    });

    $('#send-code').click(function() {
    var email = $('input[name="email"]').val().trim();
    if (validationState.email) {
        $.ajax({
            url: '/api/sendVerificationCode',
            type: 'POST',
            data: { email: email },
            success: function(response) {
                if (response === 'SENT') {
                    startTimer();
                    $('.email-message').text('인증번호가 전송되었습니다.').removeClass('error').addClass('success');
                } else {
                    $('.email-message').text('인증번호 전송에 실패했습니다.').removeClass('success').addClass('error');
                }
            },
            error: function() {
                console.error("AJAX 요청이 실패했습니다.");
                $('.email-message').text('인증번호 전송에 실패했습니다.').removeClass('success').addClass('error');
            }
        });
    } else {
        $('.email-message').text('이메일 주소가 유효하지 않습니다.').removeClass('success').addClass('error');
    }
});

// 인증번호 유효성 검사
$('input[name="verification_code"]').on('input', function() {
    var verificationCode = $(this).val().trim();

    if (verificationCode === "") {
        $('.verification-message').text('');
        validationState.verificationCode = false;
    } else {
        $.ajax({
            url: '/api/verifyCode',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                email: $('input[name="email"]').val().trim(),
                verificationCode: verificationCode
            }),
            success: function(response) {
                if (response.valid) {
                    $('.verification-message').text('인증번호가 확인되었습니다.').removeClass('error').addClass('success');
                    validationState.verificationCode = true;
                } else {
                    $('.verification-message').text('인증번호가 올바르지 않습니다.').removeClass('success').addClass('error');
                    validationState.verificationCode = false;
                }
                toggleSubmitButton();
            },
            error: function() {
                console.error("AJAX 요청이 실패했습니다.");
                $('.verification-message').text('인증번호 확인에 실패했습니다.').removeClass('success').addClass('error');
            }
            });
        }
    });


    let timerInterval; // 타이머 인터벌 변수
let isTimerRunning = false; // 타이머가 현재 실행 중인지 여부

// 타이머 함수
function startTimer() {
    const timer = $('#timer');
    let duration = 5 * 60; // 5분
    let minutes, seconds;

    // 타이머 업데이트 함수
    function updateTimer() {
        minutes = Math.floor(duration / 60);
        seconds = duration % 60;

        minutes = minutes < 10 ? '0' + minutes : minutes;
        seconds = seconds < 10 ? '0' + seconds : seconds;

        timer.text(minutes + ":" + seconds);

        if (duration <= 0) {
            clearInterval(timerInterval);
            timer.text('00:00');
            $('#send-code').prop('disabled', false); // 버튼 활성화
            $('.email-message').text('인증번호 유효 시간이 만료되었습니다.').removeClass('success').addClass('error');
            isTimerRunning = false;
        } else {
            duration--;
        }
    }

    if (isTimerRunning) {
        clearInterval(timerInterval); // 기존 타이머 중지
    }

    timerInterval = setInterval(updateTimer, 1000);
    updateTimer();
    $('#send-code').prop('disabled', true); // 버튼 비활성화
    isTimerRunning = true;
}

    // 폼 제출 이벤트
    $('#registration-form').on('submit', function(event) {
        event.preventDefault(); // 기본 제출 방지

        if (Object.values(validationState).every(value => value === true)) {
            $.ajax({
                url: '/api/register',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    memberId: $('input[name="username"]').val(),
    				memberPasswd: $('input[name="password"]').val(),
    				memberName: $('input[name="name"]').val(),
    				memberTel: $('input[name="phone"]').val(),
    				memberEmail: $('input[name="email"]').val(),
    				verificationCode: $('input[name="verification_code"]').val()
                }),
                success: function(response) {
                    alert('회원가입이 성공적으로 완료되었습니다.');
                    window.location.href = '/main'; // 회원가입 성공 후 로그인 페이지로 이동
                },
                error: function() {
                    alert('회원가입에 실패했습니다.');
                }
            });
        } else {
            alert('양식이 올바르게 입력되지 않았습니다.');
        }
    });
});
console.log("아이디:", $('input[name="username"]').val());
console.log("비밀번호:", $('input[name="password"]').val());
console.log("이름:", $('input[name="name"]').val());
console.log("전화번호:", $('input[name="phone"]').val());
console.log("이메일:", $('input[name="email"]').val());
console.log("인증번호:", $('input[name="verification_code"]').val());