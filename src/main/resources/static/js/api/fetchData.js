let baseURL = "http://localhost:8091";

export async function fetchData(path) {
  const fullURL = new URL(path, baseURL).toString();
  try {
    const response = await fetch(fullURL);
    return await response.json();
  } catch (error) {
    console.error("Error fetching data: ", error);
    throw error;
  }
}