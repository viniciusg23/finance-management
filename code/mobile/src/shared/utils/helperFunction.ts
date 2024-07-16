export const handleHeaders = (headers: Headers, token?: string | null) => {
    headers.append("ngrok-skip-browser-warning", "123");
    headers.append("Content-Type", "application/json");
    if(token)
        headers.append('Authorization', token);
}