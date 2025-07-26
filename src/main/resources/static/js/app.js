const deleteBtns = document.querySelectorAll(".deleteBtn")
const deleteModal = document.querySelector(".deleteModal")
const closeBtn = document.getElementById("closeBtn")
const confirmMsg = document.getElementById("confirmMsg")

deleteBtns.forEach(deleteBtn => {
  deleteBtn.addEventListener("click", () => {

    console.log("clicked")

    const id = deleteBtn.dataset.pizzaid;

    const pizzaName = deleteBtn.dataset.pizzaname;

    const ingredientId = deleteBtn.dataset.ingredientid;

    const ingredientName = deleteBtn.dataset.ingredientname;

    console.log(ingredientId, ingredientName)

    console.log(pizzaName)

    const deleteForm = document.querySelector(".deleteForm")

    if (id) {
      deleteForm.action = `/pizzas/delete/${id}`
      confirmMsg.innerHTML = `Are you sure you want to delete: ${pizzaName} Pizza?`
    }

    deleteForm.action = `/ingredients/delete/${ingredientId}`
    confirmMsg.innerHTML = `Are you sure you want to delete this ingredient: ${ingredientName} ?`

    deleteModal.classList.remove("d-none")



  })
})


closeBtn.addEventListener("click", () => {

  console.log("clicked")
  console.log(deleteModal.classList)
  deleteModal.classList.add("d-none")
  console.log(deleteModal.classList)

})
