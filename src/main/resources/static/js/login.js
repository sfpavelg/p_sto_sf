const keyName = 'kataToken';

if (document.URL.endsWith('logout')) {
    logOut();
}

init();


function init() {
    let loginButton = document.getElementById('loginButton');
    let email = document.getElementById('email');
    let password = document.getElementById('password');
    loginButton.addEventListener('click', () => getTokenData(
        email.value,
        password.value,
        document.getElementById('rememberCheckBox').checked
    ));
}

async function saveToken(tokenData) {
    const json = await tokenData.then();
    const tokenValue = json.token.replaceAll('\"', '');
    const timeExpire = json.timeExpire;

    document.cookie = `${keyName} = ${tokenValue}; max-age=${timeExpire};`
}

function getTokenData(email, password, remember) {
    return fetch('api/auth/token', {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            email,
            password,
            remember
        }),
    })
        .then((res) => {
            if (res.status === 200) {
                const tokenData = res.json();
                saveToken(tokenData);
                location.href = '/users'
                return Promise.resolve()
            }
            alert('Неверный пароль');
            return Promise.reject();
        });

}

function logOut() {
    document.cookie = `${keyName} = null; max-age=-1;`
}