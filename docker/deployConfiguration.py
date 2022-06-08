import glob
import yaml
import sys

files = glob.glob('../**/resources/bootstrap.yml', recursive=True);
URL = 'http://{}:8880'

for file in files:
    with open(file, 'r') as f:
        data = yaml.safe_load(f.read())
        data['spring']['cloud']['config']['uri'] = URL.format(sys.argv[1])
        payload = yaml.safe_dump(data);
        print(payload)
        with open(file, 'w') as f:
            f.write(payload)
            f.close()