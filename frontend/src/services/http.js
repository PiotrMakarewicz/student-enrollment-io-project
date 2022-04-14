import { toast } from "react-toastify";

const serverUrl = "http://localhost:8080";

function optionsObjectToString(options) {
  if (!options) return "";

  let optionsSting;

  Object.entries(options).forEach((value, key) => {
    if (!optionsSting) optionsSting = `?${value[0]}=${value[1]}`;
    else optionsSting += `&${value[0]}=${value[1]}`;
  });

  return optionsSting;
}
async function proccesResponse(response, toastComunicat) {
  if (response.ok) {
    toast.success(`${toastComunicat}`);
    const data = await response.json();

    return {
      ok: true,
      data,
    };
  } else {
    const text = await response.text();
    toast.error(`${toastComunicat} \n ${text}`);
    return {
      ok: false,
      data: text,
    };
  }
}

const get = async (path, options) => {
  const response = await fetch(
    serverUrl + path + optionsObjectToString(options),
    {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    }
  );

  return await proccesResponse(response, "GET " + path);
};

const post = async (path, body) => {
  const response = await fetch(serverUrl + path, {
    method: "POST",
    body: JSON.stringify(body),
    headers: {
      "Content-Type": "application/json",
    },
  });

  return await proccesResponse(response, "POST " + path);
};

const http = {
  get,
  post,
};

export default http;
