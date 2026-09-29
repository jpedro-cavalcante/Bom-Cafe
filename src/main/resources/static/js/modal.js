document.addEventListener("DOMContentLoaded", function () {
    const togglePassword = document.querySelector(".toggle-password");
    if (togglePassword) {
        togglePassword.addEventListener("click", function (e) {
            e.preventDefault();
            const pwd = document.querySelector("#senha");
            if (pwd.type === "password") {
                pwd.type = "text";
                this.textContent = "🙈";
            } else {
                pwd.type = "password";
                this.textContent = "👁";
            }
        });
    }

    const modal = document.querySelector(".modal-overlay");
    if (modal) {
        const closeModal = () => modal.classList.add("hidden");

        document.querySelectorAll("[data-modal-target]").forEach((btn) => {
            btn.addEventListener("click", () => modal.classList.remove("hidden"));
        });

        document.querySelector(".modal-close")?.addEventListener("click", closeModal);
        modal.addEventListener("click", (e) => {
            if (e.target === modal) closeModal();
        });
        document.addEventListener("keydown", (e) => {
            if (e.key === "Escape" && !modal.classList.contains("hidden")) closeModal();
        });
    }

    document.querySelectorAll(".tab-btn").forEach((btn) => {
        btn.addEventListener("click", () => {
            const target = btn.dataset.tab;
            document.querySelectorAll(".tab-btn").forEach((b) => b.classList.remove("active"));
            document.querySelectorAll(".tab-panel").forEach((panel) => {
                panel.classList.remove("active");
                panel.classList.add("hidden");
            });
            btn.classList.add("active");
            const selectedPanel = document.getElementById(target);
            if (selectedPanel) {
                selectedPanel.classList.remove("hidden");
                selectedPanel.classList.add("active");
            }
        });
    });
});
