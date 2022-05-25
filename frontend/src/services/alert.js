/**
 * 
 * @param {String} message 
 * @param {String} bgColor 
 * @param {String} color 
 * @param {Int} duration 
 * 
 * @returns function creating and displaying alert
 * @description it's good practice to use custom background and text colors in order to specify type of shown message 
 * 
 * @example <Submit value="test"
                    onSubmit={() => {
                        showAlert("Done", "bg-warning");
                    }}
            />
 */

/* 
available colors
.text-secondary
.text-success
.text-danger
.text-warning
.text-info
.text-light
.text-dark
.text-body
.text-muted
.text-white 
 */

export function showAlert(message, bgColor = "bg-primary", color = "text-light", duration = 1800) {
    const alertContainer = document.querySelector("[data-alert-container]");

    const alert = document.createElement("div");
    alert.textContent = message;
    alert.classList.add("alert");
    alert.classList.add(bgColor);
    alert.classList.add(color);
    alertContainer.prepend(alert);
    if (duration == null) return;
    setTimeout(() => {
        alert.classList.add("hide");
        alert.addEventListener("transitionend", () => {
            alert.remove();
        });
    }, duration);
}
