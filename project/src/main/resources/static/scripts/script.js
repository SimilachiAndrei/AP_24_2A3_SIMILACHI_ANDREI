document.addEventListener('DOMContentLoaded', function() {
    fetch('/api/drugs')
        .then(response => response.json())
        .then(data => {
            const tableBody = document.querySelector('#drugsTable tbody');
            data.forEach(drug => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${drug.id}</td>
                    <td>${drug.name}</td>
                    <td>${drug.quantity}</td>
                `;
                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Error fetching data:', error));
});
