document.addEventListener("DOMContentLoaded", function() {
    // Obtém os elementos HTML onde os dados serão exibidos
    var nameElement = document.getElementById("name");
    var addressElement = document.getElementById("address");
    var cityElement = document.getElementById("city");
    var stateElement = document.getElementById("state");
    var jobElement = document.getElementById("job");
    var interestsElement = document.getElementById("interests");
    var descriptionElement = document.getElementById("description");

    // Obtém os dados da URL (query string)
    var urlParams = new URLSearchParams(window.location.search);

    // Define os valores dos elementos com os dados da query string
    nameElement.textContent = urlParams.get("name");
    addressElement.textContent = urlParams.get("address");
    cityElement.textContent = urlParams.get("city");
    stateElement.textContent = urlParams.get("state");
    jobElement.textContent = urlParams.get("job");

    // Obtém a lista de áreas de interesse
    var interests = urlParams.getAll("job");
    var interestsText = "";
    interests.forEach(function(interest) {
        // Adiciona uma vírgula se não for o primeiro item
        if (interestsText !== "") {
            interestsText += ", ";
        }
        // Adiciona o interesse ao texto
        switch (interest) {
            case "computing":
                interestsText += "Computação";
                break;
            case "biology":
                interestsText += "Biologia";
                break;
            case "environment":
                interestsText += "Meio Ambiente";
                break;
            case "engineering":
                interestsText += "Engenharia";
                break;
            case "history":
                interestsText += "História";
                break;
            default:
                // Adicione outras áreas conforme necessário
                break;
        }
    });

    // Exibe as áreas de interesse na página
    interestsElement.textContent = interestsText;
    descriptionElement.textContent = urlParams.get("description");
});
