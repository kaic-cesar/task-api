 const modal = document.getElementById("taskModal");
 const url = "http://localhost:8080/v1/tasks";



    function openModal() {
        modal.style.display = "flex";
    }


    function closeModal() {

        modal.style.display = "none";

        document.getElementById("taskName").value = "";
        document.getElementById("taskDescription").value = "";
    }

    async function getTasks() {
        try{
            const response = await fetch(url);
            const tasks = await response.json();

            const taskList = document.getElementById("taskList");
            taskList.innerHTML = "";

            console.log(tasks);

            tasks.forEach((task) => {
                const taskElement = document.createElement("div");
                taskElement.classList.add("task");
                taskElement.innerHTML = `
                    <h2>${task.title}</h2>
                    <p>${task.description}</p>
                    <div class="task-actions">
                        <p>Criada em: ${task.createAt}</p>
                        <div>
                            <button class="btn-complete" onclick="completeTask(${task.id})">
                                ${(task.completed === "PENDING" ? "Concluir" : "Reabrir")}
                            </button>
                            <button class="btn-delete" onclick="deleteTask(${task.id})">Excluir</button>
                        </div>
                    </div>
                `;
                taskList.appendChild(taskElement);
            });
        } catch (error) {
            console.error("Erro ao buscar tarefas:", error);
        }
    };

    document.addEventListener("DOMContentLoaded", getTasks);


    async function saveTask() {

         const title = document.getElementById("taskName").value.trim();
         const description = document.getElementById("taskDescription").value.trim();

        if(title === "" || description === "") {
            alert("Preencha todos os campos.");
            return;
        }


        try{
            const response = await fetch(url, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                 body: JSON.stringify({
                    title: title,
                    description: description,
                 })
            });

          
            if(!response.ok) {
                throw new Error("Erro ao salvar a tarefa.");
            }

            const savedTask = await response.json();
            const taskList = document.getElementById("taskList");
            const task = document.createElement("div");
            task.dataset.id = savedTask.id;

            task.classList.add("task");

            task.innerHTML = `
            <h2>${title}</h2>
            <p>${description}</p>


            <div class="task-actions">
                <p>Criada em: ${task.createAt}</p>
                <div>
                    <button
                        class="btn-complete"
                        onclick="completeTask(this)"
                    >
                        Concluir
                    </button>

                <button
                        class="btn-delete"
                        onclick="deleteTask(${savedTask.id})"
                    >
                     Excluir
                </button>
                </div>
            </div>`;

            taskList.appendChild(task);

            console.log(savedTask);
            closeModal();

        } catch (error) {
            console.error("Erro ao salvar a tarefa:", error);   
        };
    }

    async function deleteTask(taskId) {
        try{
            const response = await fetch(`${url}/${taskId}`, {
                method: "DELETE",
            });

            if(!response.ok) {
                console.log("erro ao deletar");
            }

            console.log(`Tarefa com ID ${taskId} excluída com sucesso.`);
            getTasks(); 

        } catch (error) {
            console.error("Erro ao excluir a tarefa:", error);
        }
    }


    async function completeTask(taskId) {
        try {
            const response = await fetch(`${url}/${taskId}`, {
                method: "PATCH"
            })

            if (!response.ok) {
                throw new Error("Erro ao concluir a tarefa.");
            }

            const updatedTask = await response.json();
            console.log(`Tarefa com ID ${taskId} concluída com sucesso.`, updatedTask);

            getTasks();
        } catch (error) {   
            console.error("Erro ao concluir a tarefa:", error);
        }
    }


    window.onclick = function(event) {

        if (event.target === modal) {
            closeModal();
        }

    };