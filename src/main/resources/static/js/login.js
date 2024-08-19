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
        var allValid = Object.values(validationState).every(function(value) {
            return value === true;
        });
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
                url: '/register/checkUsername',  // 서버의 컨트롤러 URL
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
        var regex = /^[가-힣]+$/;

        if (!regex.test(name)) {
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
        // 영문자, 숫자, 특수문자 포함, 8자 이상
        var regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

        if (!regex.test(password)) {
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

    // 전화번호 유효성 검사 (숫자만 허용)
    $('input[name="phone"]').on('input', function() {
        var phone = $(this).val();
        var regex = /^[0-9]*$/;

        if (!regex.test(phone)) {
            $('.phone-message').text('전화번호는 숫자만 입력할 수 있습니다.').removeClass('success').addClass('error');
            validationState.phone = false;
        } else {
            $('.phone-message').text('');
            validationState.phone = true;
        }
        toggleSubmitButton();
    });

    // 이메일 유효성 검사 및 AJAX 체크
    $('input[name="email"]').on('input', function() {
        var email = $(this).val().trim();

        if (email === "") {
            $('.email-message').text('');
            validationState.email = false;
        } else {
            $.ajax({
                url: '/register/validateEmail',  // 서버의 컨트롤러 URL
                type: 'GET',
                data: { email: email },
                success: function(response) {
                    if (response === 'INVALID') {
                        $('.email-message').text('유효한 이메일 주소를 입력해주세요.').removeClass('success').addClass('error');
                        validationState.email = false;
                    } else if (response === 'VALID') {
                        $('.email-message').text('');
                        validationState.email = true;
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

    // 이메일 인증번호 입력 유효성 검사
    $('input[name="verification_code"]').on('input', function() {
        var code = $(this).val().trim();

        if (code === "") {
            $('.verification-message').text('인증번호를 입력해주세요.').removeClass('success').addClass('error');
            validationState.verificationCode = false;
        } else {
            $('.verification-message').text('');
            validationState.verificationCode = true;
        }
        toggleSubmitButton();
    });

    // 인증번호 발송 버튼 클릭 시 이메일 인증번호 발송
    $('#send-code').on('click', function() {
        var email = $('input[name="email"]').val().trim();

        if (!validateEmail(email)) {
            alert('유효한 이메일 주소를 입력해주세요.');
            return;
        }

        $.ajax({
            url: '/sendVerificationCode', // 서버의 컨트롤러 URL
            type: 'POST',
            data: { email: email },
            success: function(response) {
                if (response === 'SENT') {
                    alert('인증번호가 전송되었습니다.');
                } else {
                    alert('인증번호 전송에 실패했습니다.');
                }
            },
            error: function() {
                console.error("AJAX 요청이 실패했습니다.");
                alert('서버 오류로 인증번호 전송에 실패했습니다.');
            }
        });
    });

    function validateEmail(email) {
        var regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return regex.test(email);
    }

    // 회원가입 버튼 클릭 시 데이터 전송 부분
    $('form').on('submit', function(event) {
        event.preventDefault(); // 기본 제출 이벤트 막기

        if ($('input[type="submit"]').prop('disabled')) {
            return; // 버튼이 비활성화된 상태라면 종료
        }

        var formData = {
            memberId: $('input[name="username"]').val(),
            memberPasswd: $('input[name="password"]').val(),
            memberName: $('input[name="name"]').val(),
            memberTel: $('input[name="phone"]').val(),
            memberEmail: $('input[name="email"]').val(),
            memberJoinDate: new Date().toISOString().split('T')[0], // 현재 날짜를 기본 값으로 설정
            memberImage: '',  // 이미지 업로드 기능이 없는 경우 기본 값으로 빈 문자열 사용
            memberIntro: ''  // 소개문 입력이 없는 경우 빈 문자열 사용
        };

        $.ajax({
            url: '/register', // 서버의 회원가입 처리 컨트롤러 URL
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function(response) {
                if (response === 'SUCCESS') {
                    alert('회원가입이 성공적으로 완료되었습니다.');
                    window.location.href = '/login'; // 로그인 페이지로 리다이렉트
                } else {
                    alert('회원가입에 실패했습니다. 다시 시도해주세요.');
                }
            },
            error: function() {
                console.error("회원가입 요청이 실패했습니다.");
                alert('서버 오류가 발생했습니다. 나중에 다시 시도해주세요.');
            }
        });
    });
});
