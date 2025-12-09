function loadMovies() {
    fetch("/movies")
        .then(res => res.json())
        .then(data => {
            let list = document.getElementById("movieList");
            list.innerHTML = "";

            data.forEach(m => {
                list.innerHTML += `<li>${m.title} (${m.genre})</li>`;
            });
        })
        .catch(err => console.error(err));
}
