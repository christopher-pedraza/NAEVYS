# Libreria necesaria para hacer peticiones a paginas web
import requests

# Obtener el archivo JSON del ultimo release del repositorio
response = requests.get("https://api.github.com/repos/christopher-pedraza/NAEVYS/releases/latest").json()

# Crea una lista con los datos de los assets (archivos del release)
list = response["assets"]

# Especifica al usuario la version que se esta actualizando
print("~~~ Version '" + response["name"] + "' ~~~\n")

# Recorre la lista de los assets
for asset in list:
	# Despliega el nombre del archivo
	print("Downloading '" + asset["name"] + "'", end=" .")
	# Obtiene el archivo a partir del enlace del asset
	file_dir = requests.get(asset["browser_download_url"])
	print(".", end="")
	# Crea el archivo
	open(asset["name"], "wb").write(file_dir.content)
	print(". ", end="")
	print("Finished.")

# Espera un input para que no se cierre la terminal
input("\nPress any key to continue...")