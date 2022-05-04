import { toast } from "react-toastify";

import {_download} from "./downloader";

const serverUrl = "http://localhost:8080";

function optionsObjectToString(options) {
    if (!options) return "";

    let optionsSting;

<<<<<<< HEAD
  Object.entries(options).forEach((value) => {
    if (!optionsSting) optionsSting = `?${value[0]}=${value[1]}`;
    else optionsSting += `&${value[0]}=${value[1]}`;
  });
=======
    Object.entries(options).forEach((value) => {
        if (!optionsSting) optionsSting = `?${value[0]}=${value[1]}`;
        else optionsSting += `&${value[0]}=${value[1]}`;
    });
>>>>>>> develop

    return optionsSting;
}
async function proccesResponse(response, toastComunicat) {
    if (response.ok) {
        toast.success(`${toastComunicat}`);
        const data = await response.json();

        return {
            ok: true,
<<<<<<< HEAD
            data,
=======
            data
>>>>>>> develop
        };
    } else {
        const text = await response.text();
        toast.error(`${toastComunicat} \n ${text}`);
        return {
            ok: false,
<<<<<<< HEAD
            data: text,
=======
            data: text
>>>>>>> develop
        };
    }
}

const download = async (path, filename, extension) => {
    const response = await fetch(serverUrl + path, {
        method: "GET"
    });
    const reader = response.body.getReader();
    var bytes = (await reader.read()).value;
    _download(bytes, filename, extension);
};


const get = async (path, options) => {
<<<<<<< HEAD
    const response = await fetch(
        serverUrl + path + optionsObjectToString(options),
        {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
        }
    );
=======
    const response = await fetch(serverUrl + path + optionsObjectToString(options), {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    });
>>>>>>> develop

    return await proccesResponse(response, "GET " + path);
};

const post = async (path, body) => {
    const response = await fetch(serverUrl + path, {
        method: "POST",
        body: JSON.stringify(body),
        headers: {
<<<<<<< HEAD
            "Content-Type": "application/json",
        },
=======
            "Content-Type": "application/json"
        }
>>>>>>> develop
    });
    return await proccesResponse(response, "GET " + path);
};

const http = {
    get,
    post,
<<<<<<< HEAD
=======
    download
>>>>>>> develop
};

export default http;
