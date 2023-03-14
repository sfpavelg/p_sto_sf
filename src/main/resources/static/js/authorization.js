let token = localStorage.getItem('tokenData')
if(token===null){
    showError403();
}
let bearerToken = 'Bearer ' + token;

fetch('/api/auth/validate', {
    method: 'GET',
    credentials: 'include',
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': bearerToken
    }
})
    .then(resp => {
        if(resp.status===403){
            showError403();
        }
    })
function showError403(){
    location.href='/403';
}