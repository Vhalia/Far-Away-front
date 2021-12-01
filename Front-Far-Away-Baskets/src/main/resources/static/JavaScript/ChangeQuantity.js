$("#newQuantity").on("change", updateRedirect);
function updateRedirect() {
    let url = new URL(window.location.href)
    let params = url.searchParams
    url = "http://localhost:8003/update/baskets/1/" + $("#idProduct").val() + "?quantity=" + $("#newQuantity").val()
    window.location = url.toString();
}