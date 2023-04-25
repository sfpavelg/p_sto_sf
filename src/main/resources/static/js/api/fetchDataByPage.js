let baseURL = "http://localhost:8091";

export async function fetchDataByPage(path, page, itemsPerPage) {
  const fullURL = new URL(path, baseURL);
  fullURL.searchParams.set("page", page);
  fullURL.searchParams.set("items", itemsPerPage);
  try {
    const response = await fetch(fullURL.toString());
    return await response.json();
  } catch (error) {
    console.error("Error fetching data: " + error);
    throw error;
  }
}