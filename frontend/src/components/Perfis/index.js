
import React, { useEffect, useState } from 'react';
import './Perfis.css';
import { Table, Button, Modal, Input, Row, Col } from 'antd';

import api from '../../services/api';

function Perfis() {

    const [isModalVisible, setIsModalVisible] = useState(false);
    const [input, setInput] = useState("");

    const [allPerfis, setAllPerfis] = useState([]);

    const columns = [
        {
            title: 'Nome',
            dataIndex: 'nome',
            key: 'nome',
        },
        {
            title: 'Deletar',
            dataIndex: 'remover',
            key: 'deletar',
            render: (remover, item) => (
                remover
                    ? <Button type="danger" onClick={() => removerPerfil(item.id)}>
                        Remover perfil
                    </Button>
                    : <></>
            )
        }
    ];


    const showModal = () => {
        setIsModalVisible(true);
    };

    const handleOk = async () => {
        setIsModalVisible(false);

        await adicionarPerfil(input);

        setInput("");

        await buscarPerfis();
    };

    const handleCancel = () => {
        setIsModalVisible(false);
    };

    //API
    async function buscarPerfis() {
        try {
            const { data: perfis } = await api.get('/perfil');

            if (perfis) {
                setAllPerfis(perfis);
                console.log("perfis API", perfis);
            }
        } catch (error) {
            console.log("Ops ..", error);
        }
    }

    async function adicionarPerfil(nomePerfil) {
        try {

            //JSON SERVER
            let ultimaKey = allPerfis.slice(-1).pop();

            let perfil = {
                id: ultimaKey.id + 1,
                key: ultimaKey.key + 1,
                nome: nomePerfil
            };

            await api.post('/perfil', perfil);

        } catch (error) {
            console.log("Ops ..", error);
        }
    }

    async function removerPerfil(id) {
        try {
            await api.delete('/perfil/' + id);

            await buscarPerfis();

        } catch (error) {
            console.log("Ops ..", error);
        }
    }


    useEffect(() => {
        buscarPerfis();
    }, []);

    return (
        <div>
            <Row justify="end">
                <Col span={6}>
                    <Button type="primary" onClick={showModal}>
                        Criar perfil
                    </Button>
                </Col>
            </Row>

            <Modal
                title="Criar perfil"
                visible={isModalVisible}
                onOk={handleOk}
                okText="Salvar"
                onCancel={handleCancel}
                cancelText="Cancelar"
            >
                <Input
                    placeholder="Digite o nome do perfil"
                    onChange={(t) => { setInput(t.target.value) }}
                    value={input}
                />
            </Modal>
            <br />
            {allPerfis.length > 0 && <Table
                dataSource={allPerfis}
                columns={columns} />}

        </div>
    );
}

export default Perfis;
