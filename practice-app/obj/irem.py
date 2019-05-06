from ibm_watson import VisualRecognitionV3
from urllib.parse import urlencode
import json


visual_recognition = VisualRecognitionV3('2018-03-19',iam_apikey = 'wNhX6Wogs-1OgmHY_5efLHWC2Abv-dZVJ6rogx5KuqlB')
picture_url = 'https://watson-developer-cloud.github.io/doc-tutorial-downloads/visual-recognition/640px-IBM_VGA_90X8941_on_PS55.jpg'
classes_result = visual_recognition.classify(url=urlencode(picture_url)).get_result()
print(json.dumps(classes_result,indent = 2))
