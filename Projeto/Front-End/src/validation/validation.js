if(localStorage.getItem('token') == null) {
    window.location.href = "../../auth.html";
}

function logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    window.location.href = "../../auth.html";
};