$(document).ready(function() {
    // 아이디 중복 체크 및 입력 제한 (영어와 숫자만 허용)
    $('input[name="username"]').on('input', function() {
        var username = $(this).val();
        var regex = /^[a-zA-Z0-9]*$/;

        if (!regex.test(username)) {
            $('.message').text('아이디는 영어와 숫자만 입력할 수 있습니다.').removeClass('success').addClass('error');
        } else if (username === "") {
            $('.message').text('').removeClass('success error');
        } else {
            $.ajax({
                url: '/checkUsername',  // 서버의 컨트롤러 URL
                type: 'GET',
                data: { username: username },
                success: function(response) {
                    if (response === 'EXIST') {
                        $('.message').text('이미 존재하는 아이디입니다.').removeClass('success').addClass('error');
                    } else if (response === 'AVAILABLE') {
                        $('.message').text('사용 가능한 아이디입니다.').removeClass('error').addClass('success');
                    } else if (response === 'INVALID') {
                        $('.message').text('아이디는 영어와 숫자만 입력할 수 있습니다.').removeClass('success').addClass('error');
                    }
                },
                error: function() {
                    console.error("AJAX 요청이 실패했습니다.");
                }
            });
        }
    });

    // 이름 필드에 한글만 입력되도록 제한
    $('input[name="name"]').on('input', function() {
        var name = $(this).val().trim();
        var regex = /^[가-힣]+$/;

        if (!regex.test(name)) {
            $('.name-message').text('이름은 한글만 입력할 수 있습니다.').removeClass('success').addClass('error');
        } else {
            $('.name-message').text('');
        }
    });

    // 비밀번호 유효성 체크
    $('input[name="password"]').on('input', function() {
        var password = $(this).val();
        var regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;

        if (!regex.test(password)) {
            $('.password-message').text('비밀번호는 영문자와 숫자를 포함하여 8자리 이상이어야 합니다.').removeClass('success').addClass('error');
        } else {
            $('.password-message').text(''); // 유효한 경우 메시지 삭제
        }

        checkPasswordsMatch(); // 비밀번호 일치 여부 확인
    });

    // 비밀번호 확인 유효성 체크
    $('input[name="confirm_password"]').on('input', function() {
        checkPasswordsMatch();
    }).on('focus', function() {
        checkPasswordsMatch();
    }).on('blur', function() {
        $('.confirm-password-message').text(''); // 포커스가 벗어났을 때 메시지 제거
    });

    function checkPasswordsMatch() {
        var password = $('input[name="password"]').val();
        var confirmPassword = $('input[name="confirm_password"]').val();

        if (confirmPassword === "" || password === "") {
            $('.confirm-password-message').text(''); // 비밀번호 또는 확인 비밀번호가 비어 있으면 메시지 숨김
        } else if (password !== confirmPassword) {
            $('.confirm-password-message').text('비밀번호가 일치하지 않습니다.').removeClass('success').addClass('error');
        } else {
            $('.confirm-password-message').text(''); // 일치하는 경우 메시지 삭제
        }
    }

    // 전화번호 필드에 숫자만 입력되도록 제한
    $('input[name="phone"]').on('input', function() {
        var phone = $(this).val();
        var regex = /^[0-9]*$/;

        if (!regex.test(phone)) {
            $('.phone-message').text('전화번호는 숫자만 입력할 수 있습니다.').removeClass('success').addClass('error');
        } else {
            $('.phone-message').text('');
        }
    });

    // 이메일 입력 필드의 유효성 검사
    $('input[name="email"]').on('input', function() {
        var email = $(this).val().trim();

        if (email === "") {
            $('.email-message').text(''); // 이메일 필드가 비어 있으면 메시지 숨김
        } else {
            $.ajax({
                url: '/api/validateEmail',  // 서버의 컨트롤러 URL
                type: 'GET',
                data: { email: email },
                success: function(response) {
                    if (response === 'INVALID') {
                        $('.email-message').text('유효한 이메일 주소를 입력해주세요.').removeClass('success').addClass('error');
                    } else if (response === 'VALID') {
                        $('.email-message').text(''); // 유효한 이메일 주소인 경우 메시지 제거
                    }
                },
                error: function() {
                    console.error("AJAX 요청이 실패했습니다.");
                }
            });
        }
    });

    // 이메일 인증 버튼 클릭 이벤트
    $('#send-code').on('click', function() {
        var email = $('input[name="email"]').val();

        if (!validateEmail(email)) {
            $('.email-message').text('유효한 이메일 주소를 입력해주세요.').removeClass('success').addClass('error');
            return;
        }

        $.ajax({
            url: '/api/sendVerificationCode', // 서버의 컨트롤러 URL
            type: 'POST',
            data: { email: email },
            success: function(response) {
                if (response === 'SENT') {
                    $('.email-message').text('인증번호가 전송되었습니다.').removeClass('error').addClass('success');
                } else {
                    $('.email-message').text('인증번호 전송에 실패했습니다.').removeClass('success').addClass('error');
                }
            },
            error: function() {
                console.error("AJAX 요청이 실패했습니다.");
            }
        });
    });

    // 인증번호 입력 필드 유효성 검사
    $('input[name="verification_code"]').on('input', function() {
        var code = $(this).val().trim();

        if (code === "") {
            $('.verification-message').text('인증번호를 입력해주세요.').removeClass('success').addClass('error');
        } else {
            $('.verification-message').text(''); // 유효한 경우 메시지 삭제
        }
    });

    function validateEmail(email) {
        var regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return regex.test(email);
    }
});
