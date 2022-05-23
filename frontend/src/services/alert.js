export function showAlert(message, color = "bg-primary", duration = 1200) {
    const alertContainer = document.querySelector("[data-alert-container]");

    const alert = document.createElement("div");
    alert.textContent = message;
    alert.classList.add("alert");
    alert.classList.add(color);
    alertContainer.prepend(alert);
    if (duration == null) return;
    setTimeout(() => {
        alert.classList.add("hide");
        alert.addEventListener("transitionend", () => {
            alert.remove();
            console.log("elo");
        });
    }, duration);
}
