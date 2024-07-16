export const handleHeaders = (headers: Headers, token?: string | null) => {
    headers.append("ngrok-skip-browser-warning", "123");
    headers.append("Content-Type", "application/json");
    if(token)
        headers.append('Authorization', token);
}

export const formatDate = (dateString: string) => {
    const date = new Date(dateString);
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Months are zero-based in JavaScript
    const year = date.getFullYear();
    return `${day}/${month}/${year}`;
}