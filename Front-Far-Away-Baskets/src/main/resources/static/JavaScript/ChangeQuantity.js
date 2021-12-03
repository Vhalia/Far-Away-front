$(".quantity").on("change", updateRedirect)

function updateRedirect(e) {
    let idUser = e.target.dataset.iduser
    let idProd = e.target.dataset.idprod
    let url = new URL(window.location.href)
    let params = url.searchParams
    url = "http://localhost:8003/update/baskets/" + idUser+"/" + idProd + "?quantity=" + e.target.value
    window.location = url.toString();
}